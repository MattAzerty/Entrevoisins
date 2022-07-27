package com.openclassrooms.entrevoisins.events;

public class TabEvent {


    /**
     * Tab selected
     */
    public int selectedTab;

    /**
     * Constructor.
     * @param selectedTab
     */
    public TabEvent(int selectedTab) {
        this.selectedTab = selectedTab;
    }
}
