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
import java.util.Objects;

/**
 * Address builder
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class AddressBuilder
        implements Serializable
{
    private static final long serialVersionUID = -1501418953979559654L;

    private String street;

    private String city;

    private String country;

    public String getStreet()
    {
        return street;
    }

    public void setStreet( String street )
    {
        this.street = street;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry( String country )
    {
        this.country = country;
    }

    public String build()
    {
        StringBuilder builder = new StringBuilder();

        if ( this.street != null )
        {
            builder.append( this.street );
            if ( this.city != null )
            {
                builder.append( " " );
            }
        }
        if ( this.city != null )
        {
            builder.append( this.city );
            if ( this.country != null )
            {
                builder.append( " " );
            }
        }
        if ( this.country != null )
        {
            builder.append( this.country );
        }

        return builder.toString();
    }

    public boolean isSame( AddressBuilder address )
    {
        AddressBuilder thisAddress = new AddressBuilder();

        thisAddress.setStreet( this.street );
        thisAddress.setCity( this.city );
        thisAddress.setCountry( this.country );

        return Objects.equals( thisAddress, address );
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        AddressBuilder that = ( AddressBuilder ) o;

        if ( city != null ? !city.equals( that.city ) : that.city != null ) return false;
        if ( country != null ? !country.equals( that.country ) : that.country != null ) return false;
        //noinspection RedundantIfStatement
        if ( street != null ? !street.equals( that.street ) : that.street != null )
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = street != null ? street.hashCode() : 0;
        result = 31 * result + ( city != null ? city.hashCode() : 0 );
        result = 31 * result + ( country != null ? country.hashCode() : 0 );
        return result;
    }
}
