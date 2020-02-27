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

/**
 * The terms and conditions content contract.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class TermsContent
        extends PublicContent
{
    private static final long serialVersionUID = -2170922238755755399L;

    private String fullTerms;

    public TermsContent()
    {
    }

    public TermsContent( String name )
    {
        super( name );
    }

    public String getFullTerms()
    {
        return fullTerms;
    }

    public void setFullTerms( String fullTerms )
    {
        this.fullTerms = fullTerms;
    }
}
