/*
 * Copyright 2018 Comvai, s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package biz.turnonline.ecosystem.origin.frontend.content.model;

import com.google.common.collect.ComparisonChain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The event public content contract.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class EventContent
        extends ProductContent
{
    private static final long serialVersionUID = -6059097482628668645L;

    private Boolean where;

    private WhereAddress whereAddress;

    private Date beginAt;

    private Integer timeBeginAtFirst;

    private Integer timeBeginAtLast;

    private Date endAt;

    private Integer timeEndAtFirst;

    private Integer timeEndAtLast;

    private List<EventPart> eventParts = new ArrayList<>();

    public EventContent()
    {
    }

    public EventContent( String name )
    {
        super( name );
    }

    public void add( EventPart item )
    {
        checkNotNull( item );

        if ( !eventParts.contains( item ) )
        {
            eventParts.add( item );
        }
    }

    public List<EventPart> getEventParts()
    {
        Collections.sort( eventParts );
        return eventParts;
    }

    public void setEventParts( List<EventPart> eventParts )
    {
        clearEventParts();

        for ( EventPart next : eventParts )
        {
            this.add( next );
        }
    }

    public void clearEventParts()
    {
        eventParts.clear();
    }

    public Boolean isWhere()
    {
        return where;
    }

    public void setWhere( Boolean where )
    {
        this.where = where;
    }

    public WhereAddress getWhereAddress()
    {
        return whereAddress;
    }

    public void setWhereAddress( WhereAddress whereAddress )
    {
        this.whereAddress = whereAddress;
    }

    public Date getBeginAt()
    {
        return beginAt;
    }

    public void setBeginAt( Date beginAt )
    {
        this.beginAt = beginAt;
    }

    public Integer getTimeBeginAtFirst()
    {
        return timeBeginAtFirst;
    }

    public void setTimeBeginAtFirst( Integer timeBeginAtFirst )
    {
        this.timeBeginAtFirst = timeBeginAtFirst;
    }

    public Integer getTimeBeginAtLast()
    {
        return timeBeginAtLast;
    }

    public void setTimeBeginAtLast( Integer timeBeginAtLast )
    {
        this.timeBeginAtLast = timeBeginAtLast;
    }

    public Date getEndAt()
    {
        return endAt;
    }

    public void setEndAt( Date endAt )
    {
        this.endAt = endAt;
    }

    public Integer getTimeEndAtFirst()
    {
        return timeEndAtFirst;
    }

    public void setTimeEndAtFirst( Integer timeEndAtFirst )
    {
        this.timeEndAtFirst = timeEndAtFirst;
    }

    public Integer getTimeEndAtLast()
    {
        return timeEndAtLast;
    }

    public void setTimeEndAtLast( Integer timeEndAtLast )
    {
        this.timeEndAtLast = timeEndAtLast;
    }

    @Override
    public String toString()
    {
        return "EventContent{" +
                "where=" + where +
                ", whereAddress=" + whereAddress +
                ", beginAt=" + beginAt +
                ", timeBeginAtFirst=" + timeBeginAtFirst +
                ", timeBeginAtLast=" + timeBeginAtLast +
                ", endAt=" + endAt +
                ", timeEndAtFirst=" + timeEndAtFirst +
                ", timeEndAtLast=" + timeEndAtLast +
                ", eventParts=" + eventParts +
                "} " + super.toString();
    }

    public static class EventPart
            implements Serializable, Comparable<EventPart>
    {
        private static final long serialVersionUID = -6496587142804868431L;

        private Long id;

        private Double priceExclVat;

        private Double finalPrice;

        private String title;

        private String description;

        private int sortOrder;

        public EventPart()
        {
        }

        public Long getId()
        {
            return id;
        }

        public void setId( Long id )
        {
            this.id = id;
        }

        public Double getPriceExclVat()
        {
            return priceExclVat;
        }

        public void setPriceExclVat( Double priceExclVat )
        {
            this.priceExclVat = priceExclVat;
        }

        public Double getFinalPrice()
        {
            return finalPrice;
        }

        public void setFinalPrice( Double finalPrice )
        {
            this.finalPrice = finalPrice;
        }

        public String getTitle()
        {
            return title;
        }

        public void setTitle( String title )
        {
            this.title = title;
        }

        public String getDescription()
        {
            return description;
        }

        public void setDescription( String description )
        {
            this.description = description;
        }

        public int getSortOrder()
        {
            return sortOrder;
        }

        public void setSortOrder( int sortOrder )
        {
            this.sortOrder = sortOrder;
        }

        @Override
        public int compareTo( EventPart eventPart )
        {
            return ComparisonChain.start().compare( this.sortOrder, eventPart.getSortOrder() ).result();
        }
    }
}
