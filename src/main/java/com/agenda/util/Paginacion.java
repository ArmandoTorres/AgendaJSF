package com.agenda.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Armando
 */
public class Paginacion {
    
    private final List page;
    private int currentPosition;
    private final int itemsPerPage;
    
    public Paginacion(List list, int itemsPerPage){
        if (list.isEmpty()){
            throw new NullPointerException();
        }
        this.page = new ArrayList();
        this.itemsPerPage = itemsPerPage;
        setSubList(list);
        currentPosition = 0;
    }
    
    public final void setSubList(List mainList){
        boolean flag = true;
        int to   = itemsPerPage;
        if (!page.isEmpty()){
            page.clear();
        }
        for (int from = 0; flag; from+=itemsPerPage) {
            if (to < mainList.size()){
                page.add(mainList.subList(from, to));
            } else {
                page.add(mainList.subList(from, (mainList.size()) ) );
                flag = false;
            }   
            to+=itemsPerPage;
        }
    }
    
    public boolean hasNext(){ 
        return ( page.size() > (currentPosition+1) );
    }
    
    public boolean hasPrevious(){
        return ( (currentPosition-1) >= 0);
    }
    
    public List next(){ 
        if (hasNext()) 
           return (List) page.get(++currentPosition);
        return null;
    }
    
    public List previous(){
        if (hasPrevious()) 
           return (List) page.get(--currentPosition);
        return null;
    }
    
    public List getFirtRowSet(){ 
        currentPosition = 0;
        return (List) page.get(currentPosition);
    }
    
    public List setCurrentPosition(int position){
        this.currentPosition = position;
        if (position >= 0 && position <= (page.size()-1) )
            return (List) page.get(position);
        return null;
    }
    
    public int getSize(){
        return page.size();
    }
    
    public int getCurrentPosition(){
        return currentPosition;
    }
}