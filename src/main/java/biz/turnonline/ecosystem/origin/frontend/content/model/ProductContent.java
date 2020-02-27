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

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;

import java.util.Date;
import java.util.Map;

/**
 * The product public content contract.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class ProductContent
        extends PublicContent
{
    private static final long serialVersionUID = 1711276482745246814L;

    private Long productId;

    private String productName;

    private String description;

    private Boolean comingSoon;

    private Date deadline;

    private String snippet;

    private String imageTitle;

    private String imageLegend;

    private Boolean showGooglePlus;

    private Boolean showFacebookLike;

    private Boolean showLinkedInShare;

    /**
     * Image url will be generated from product image blob key from image service
     */
    private String imageUrl;

    private Double finalPrice;

    private Double finalPriceExclVat;

    private Boolean altPrice;

    private Double altFinalPrice;

    private Double altFinalPriceExclVat;

    private String url;

    private Map<String, Object> subsidiary;

    public ProductContent()
    {
    }

    public ProductContent( String name )
    {
        super( name );
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl( String url )
    {
        this.url = url;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId( Long productId )
    {
        this.productId = productId;
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

    public Boolean isComingSoon()
    {
        return comingSoon;
    }

    public void setComingSoon( Boolean comingSoon )
    {
        this.comingSoon = comingSoon;
    }

    public Date getDeadline()
    {
        return deadline;
    }

    public void setDeadline( Date deadline )
    {
        this.deadline = deadline;
    }

    public String getSnippet()
    {
        return snippet;
    }

    public void setSnippet( String snippet )
    {
        this.snippet = snippet;
    }

    public String getImageTitle()
    {
        return imageTitle;
    }

    public void setImageTitle( String imageTitle )
    {
        this.imageTitle = imageTitle;
    }

    public String getImageLegend()
    {
        return imageLegend;
    }

    public void setImageLegend( String imageLegend )
    {
        this.imageLegend = imageLegend;
    }

    public Boolean isShowGooglePlus()
    {
        return showGooglePlus;
    }

    public void setShowGooglePlus( Boolean showGooglePlus )
    {
        this.showGooglePlus = showGooglePlus;
    }

    public Boolean isShowFacebookLike()
    {
        return showFacebookLike;
    }

    public void setShowFacebookLike( Boolean showFacebookLike )
    {
        this.showFacebookLike = showFacebookLike;
    }

    public Boolean isShowLinkedInShare()
    {
        return showLinkedInShare;
    }

    public void setShowLinkedInShare( Boolean showLinkedInShare )
    {
        this.showLinkedInShare = showLinkedInShare;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl( String imageUrl )
    {
        this.imageUrl = imageUrl;
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

    public Map<String, Object> getSubsidiary()
    {
        return MoreObjects.firstNonNull( subsidiary, ImmutableMap.<String, Object>of() );
    }

    public void setSubsidiary( Map<String, Object> subsidiary )
    {
        this.subsidiary = subsidiary;
    }

    @Override
    public String toString()
    {
        return "ProductContent{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", comingSoon=" + comingSoon +
                ", deadline=" + deadline +
                ", snippet='" + snippet + '\'' +
                ", imageTitle='" + imageTitle + '\'' +
                ", imageLegend='" + imageLegend + '\'' +
                ", showGooglePlus=" + showGooglePlus +
                ", showFacebookLike=" + showFacebookLike +
                ", showLinkedInShare=" + showLinkedInShare +
                ", imageUrl='" + imageUrl + '\'' +
                ", finalPrice=" + finalPrice +
                ", finalPriceExclVat=" + finalPriceExclVat +
                ", altPrice=" + altPrice +
                ", altFinalPrice=" + altFinalPrice +
                ", altFinalPriceExclVat=" + altFinalPriceExclVat +
                ", url='" + url + '\'' +
                ", subsidiary=" + subsidiary +
                "} " + super.toString();
    }
}
