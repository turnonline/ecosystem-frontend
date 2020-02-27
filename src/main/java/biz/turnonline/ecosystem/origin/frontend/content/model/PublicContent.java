/*
 * Copyright 2020 TurnOnline.biz s.r.o.
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

import java.io.Serializable;
import java.util.Date;

/**
 * The TurnOnline.biz public content contract.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public abstract class PublicContent
        implements Serializable, Public
{
    private static final long serialVersionUID = 4145673356574455849L;

    private String name;

    private String contentTitle;

    private String analyticsAccount;

    private String headerDescription;

    private String headerKeywords;

    private String contentLocale;

    private Date createdDate;

    private Date modificationDate;

    private SellerContent seller;

    public PublicContent()
    {
    }

    public PublicContent( String name )
    {
        this.name = name;
    }

    /**
     * Seller's properties
     *
     * @return the seller properties
     */
    public SellerContent getSeller()
    {
        return seller;
    }

    public void setSeller( SellerContent seller )
    {
        this.seller = seller;
    }

    @Override
    public final String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    @Override
    public String getContentTitle()
    {
        return contentTitle;
    }

    public void setContentTitle( String title )
    {
        this.contentTitle = title;
    }

    @Override
    public String getAnalyticsAccount()
    {
        return analyticsAccount;
    }

    public void setAnalyticsAccount( String analyticsAccount )
    {
        this.analyticsAccount = analyticsAccount;
    }

    @Override
    public String getHeaderDescription()
    {
        return headerDescription;
    }

    public void setHeaderDescription( String headerDescription )
    {
        this.headerDescription = headerDescription;
    }

    @Override
    public String getHeaderKeywords()
    {
        return headerKeywords;
    }

    public void setHeaderKeywords( String headerKeywords )
    {
        this.headerKeywords = headerKeywords;
    }

    @Override
    public String getContentLocale()
    {
        return contentLocale;
    }

    public void setContentLocale( String contentLocale )
    {
        this.contentLocale = contentLocale;
    }

    public Date getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate( Date createdDate )
    {
        this.createdDate = createdDate;
    }

    public Date getModificationDate()
    {
        return modificationDate;
    }

    public void setModificationDate( Date modificationDate )
    {
        this.modificationDate = modificationDate;
    }

    @Override
    public String toString()
    {
        return "PublicContent{" +
                "name='" + name + '\'' +
                ", contentTitle='" + contentTitle + '\'' +
                ", analyticsAccount='" + analyticsAccount + '\'' +
                ", headerDescription='" + headerDescription + '\'' +
                ", headerKeywords='" + headerKeywords + '\'' +
                ", contentLocale='" + contentLocale + '\'' +
                ", createdDate=" + createdDate +
                ", modificationDate=" + modificationDate +
                ", seller=" + seller +
                '}';
    }
}
