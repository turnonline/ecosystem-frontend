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

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Ordering;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * The mall article contract.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class MallArticle
        implements Serializable, Comparable<MallArticle>
{
    private static final long serialVersionUID = 6700385143498682746L;

    private String name;

    private String productName;

    private String description;

    private String contentLocale;

    private Boolean comingSoon;

    private Date beginAt;

    private Date endAt;

    private Double finalPrice;

    private Double finalPriceExclVat;

    private Boolean altPrice;

    private Double altFinalPrice;

    private Double altFinalPriceExclVat;

    /**
     * Image url will be generated from product image blob key from image service
     */
    private String imageUrl;

    private String productLink;

    private Map<String, Object> subsidiary;

    public MallArticle()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName( String productName )
    {
        this.productName = productName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    /**
     * The locale of the content.
     *
     * @return the locale of the content
     */
    public String getContentLocale()
    {
        return contentLocale;
    }

    public void setContentLocale( String contentLocale )
    {
        this.contentLocale = contentLocale;
    }

    public Boolean isComingSoon()
    {
        return comingSoon;
    }

    public void setComingSoon( Boolean comingSoon )
    {
        this.comingSoon = comingSoon;
    }

    public Date getBeginAt()
    {
        return beginAt;
    }

    public void setBeginAt( Date beginAt )
    {
        this.beginAt = beginAt;
    }

    public Date getEndAt()
    {
        return endAt;
    }

    public void setEndAt( Date endAt )
    {
        this.endAt = endAt;
    }

    public Double getFinalPrice()
    {
        return finalPrice;
    }

    public void setFinalPrice( Double finalPrice )
    {
        this.finalPrice = finalPrice;
    }

    public Double getFinalPriceExclVat()
    {
        return finalPriceExclVat;
    }

    public void setFinalPriceExclVat( Double finalPriceExclVat )
    {
        this.finalPriceExclVat = finalPriceExclVat;
    }

    public Boolean isAltPrice()
    {
        return altPrice;
    }

    public void setAltPrice( Boolean altPrice )
    {
        this.altPrice = altPrice;
    }

    public Double getAltFinalPrice()
    {
        return altFinalPrice;
    }

    public void setAltFinalPrice( Double altFinalPrice )
    {
        this.altFinalPrice = altFinalPrice;
    }

    public Double getAltFinalPriceExclVat()
    {
        return altFinalPriceExclVat;
    }

    public void setAltFinalPriceExclVat( Double altFinalPriceExclVat )
    {
        this.altFinalPriceExclVat = altFinalPriceExclVat;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl( String imageUrl )
    {
        this.imageUrl = imageUrl;
    }

    public String getProductLink()
    {
        return productLink;
    }

    public void setProductLink( String productLink )
    {
        this.productLink = productLink;
    }

    public Map<String, Object> getSubsidiary()
    {
        return MoreObjects.firstNonNull( subsidiary, ImmutableMap.<String, Object>of() );
    }

    public void setSubsidiary( Map<String, Object> subsidiary )
    {
        this.subsidiary = subsidiary;
    }

    @Override
    public int compareTo( MallArticle article )
    {
        // date descending sorting
        return ComparisonChain.start().compare( this.beginAt,
                article.getBeginAt(),
                Ordering.natural().nullsLast() ).result();
    }

    @Override
    public String toString()
    {
        return "MallArticle{" +
                "name='" + name + '\'' +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", contentLocale='" + contentLocale + '\'' +
                ", comingSoon=" + comingSoon +
                ", beginAt=" + beginAt +
                ", endAt=" + endAt +
                ", finalPrice=" + finalPrice +
                ", finalPriceExclVat=" + finalPriceExclVat +
                ", altPrice=" + altPrice +
                ", altFinalPrice=" + altFinalPrice +
                ", altFinalPriceExclVat=" + altFinalPriceExclVat +
                ", imageUrl='" + imageUrl + '\'' +
                ", productLink='" + productLink + '\'' +
                ", subsidiary=" + subsidiary +
                '}';
    }
}
