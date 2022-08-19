package com.event.trackevent.services;

import com.event.trackevent.domain.Event;
import com.event.trackevent.domain.EventObjFromFile;
import com.event.trackevent.repository.EventRepo;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;



@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepo eventRepo;
    @Override
    public Event saveEvent(Event event) {
        return eventRepo.save(event);
    }

    @Override
    public Event getEventById(String eventId) {
        return eventRepo.findByEventId(eventId);
    }

    @Override
    public void updateEvent(Event event) {
        eventRepo.delete(event);
        eventRepo.save(event);
    }

    @Override
    public void readFie(String filePath) {
        log.info("*********************************filePath :" + filePath);
         try {
            File file = new File(filePath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                Gson gson = new Gson();
                EventObjFromFile evtObjFrmFile = gson.fromJson(line, EventObjFromFile.class);
                Event evt = getEventById(evtObjFrmFile.getId());
                if (evt != null) {
                    if (evtObjFrmFile.getState().equalsIgnoreCase("STARTED")) {
                        evt.setStartTime(evtObjFrmFile.getTimestamp());
                        evt.setStartState(evtObjFrmFile.getState());
                    } else {
                        evt.setEndTime(evtObjFrmFile.getTimestamp());
                        evt.setEndState(evtObjFrmFile.getState());
                    }
                    evt.setFlag(evt.getEndTime() - evt.getStartTime() > 4 ? true : false);
                    evt.setDuration(evt.getEndTime() - evt.getStartTime());
                    updateEvent(evt);
                } else {
                    evt = new Event();
                    evt.setEventId(evtObjFrmFile.getId());
                    evt.setHost(evtObjFrmFile.getHost() == null ? " " : evtObjFrmFile.getHost());
                    evt.setType(evtObjFrmFile.getType() == null ? " " : evtObjFrmFile.getType());
                    if (evtObjFrmFile.getState().equalsIgnoreCase("STARTED")) {
                        evt.setStartTime(evtObjFrmFile.getTimestamp());
                        evt.setStartState(evtObjFrmFile.getState());
                    } else {
                        evt.setEndTime(evtObjFrmFile.getTimestamp());
                        evt.setEndState(evtObjFrmFile.getState());
                    }
                    evt.setFlag(false);
                    evt.setDuration(0L);
                    saveEvent(evt);
                }
            }
            br.close();
            fr.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
