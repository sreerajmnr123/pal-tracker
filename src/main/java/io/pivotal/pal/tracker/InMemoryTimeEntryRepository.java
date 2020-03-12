
package io.pivotal.pal.tracker;

import java.util.*;
import java.time.LocalDate;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> timeEntries = new HashMap<Long, TimeEntry>();

    private long entryId = 1L;

    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry createdTimeEntry = new TimeEntry(
                entryId, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        timeEntries.put(entryId, createdTimeEntry);
        TimeEntry getTimeEntry = timeEntries.get(entryId);
        entryId++;
        return getTimeEntry;
    }

    @Override
    public TimeEntry find(Long id) {
        TimeEntry fetchResult = timeEntries.get(id);
        return fetchResult;
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList(timeEntries.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        if (!timeEntries.containsKey(id)) {
            return null;
        }
        TimeEntry timeEntryOld = timeEntries.get(id);
        timeEntry.setId(timeEntryOld.getId());
        timeEntries.put(id, timeEntry);
        return timeEntries.get(id);
    }
    @Override
    public void delete(Long id) {
        TimeEntry entryTobeDeleted = timeEntries.get(id);
        timeEntries.remove(id,entryTobeDeleted);
    }

}