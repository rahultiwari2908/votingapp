package com.byjus.springboot.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.byjus.springboot.web.model.Vote;

@Service
public class VoteService {
    private static List<Vote> Votes = new ArrayList<Vote>();

    public List<String> retrieveVotes(List<Vote> list) {
        List<String> filteredVotes = new ArrayList<String>();
        for (Vote vote : list) {
        	filteredVotes.add(vote.getTitle());
        }
        HashSet<String> hs = new HashSet<String>(filteredVotes);
        filteredVotes.clear();
        for(String ss : hs) {
        	filteredVotes.add(ss);
        }
        return filteredVotes;
    }
    
    public Vote retrieveVote(int id) {
        for (Vote Vote : Votes) {
            if (Vote.getId()==id) {
                return Vote;
            }
        }
        return null;
    }

    public void updateVote(Vote Vote){
    		Votes.remove(Vote);
    		Votes.add(Vote);
    }

    public void addVote(String name, String desc, Date targetDate,
            boolean isDone) {
    //    Votes.add(new Vote(++VoteCount, name, desc, targetDate, isDone));
    }

    public void deleteVote(int id) {
        Iterator<Vote> iterator = Votes.iterator();
        while (iterator.hasNext()) {
            Vote Vote = iterator.next();
            if (Vote.getId() == id) {
                iterator.remove();
            }
        }
    }
}