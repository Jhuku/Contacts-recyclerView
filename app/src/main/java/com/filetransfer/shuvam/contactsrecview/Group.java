package com.filetransfer.shuvam.contactsrecview;

import java.util.ArrayList;

/**
 * Created by Shuvam Ghosh on 1/11/2017.
 */

public class Group  {

    private ArrayList<Person> group = new ArrayList<Person>();
    public ArrayList<Person> getGroup() {
        return group;
    }

    public void setGroup(ArrayList<Person> group) {
        this.group = group;
    }
    public void emptyGroup(){
        group.clear();

    }


}
