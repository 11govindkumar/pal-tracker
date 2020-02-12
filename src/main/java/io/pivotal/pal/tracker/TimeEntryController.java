package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry createdEntry = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity(createdEntry, HttpStatus.CREATED);
    }

    @GetMapping("/read/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry notFound = timeEntryRepository.find(timeEntryId);
        return new ResponseEntity(notFound, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> entryList = timeEntryRepository.list();
        return new ResponseEntity(entryList, HttpStatus.OK);
    }

    @PutMapping("/update/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedEntry = timeEntryRepository.update(timeEntryId, timeEntry);
        return new ResponseEntity(updatedEntry, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
       timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
