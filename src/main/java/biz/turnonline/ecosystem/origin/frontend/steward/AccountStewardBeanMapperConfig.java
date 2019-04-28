package biz.turnonline.ecosystem.origin.frontend.steward;

import ma.glasnost.orika.MapperFactory;
import org.ctoolkit.restapi.client.adapter.BeanMapperConfig;

import javax.inject.Singleton;

/**
 * The account steward model mapper configuration.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@Singleton
public class AccountStewardBeanMapperConfig
        implements BeanMapperConfig
{
    @Override
    public void config( MapperFactory factory )
    {
        factory.classMap( biz.turnonline.ecosystem.steward.model.Account.class, Account.class ).byDefault().register();
        factory.classMap( biz.turnonline.ecosystem.steward.model.AccountBusiness.class, AccountBusiness.class ).byDefault().register();
        factory.classMap( biz.turnonline.ecosystem.steward.model.AccountPersonalAddress.class, AccountPersonalAddress.class ).byDefault().register();
        factory.classMap( biz.turnonline.ecosystem.steward.model.AccountPostalAddress.class, AccountPostalAddress.class ).byDefault().register();
        factory.classMap( biz.turnonline.ecosystem.steward.model.AccountPublicContact.class, AccountPublicContact.class ).byDefault().register();
        factory.classMap( biz.turnonline.ecosystem.steward.model.Country.class, Country.class ).byDefault().register();
        factory.classMap( biz.turnonline.ecosystem.steward.model.Domain.class, Domain.class ).byDefault().register();
        factory.classMap( biz.turnonline.ecosystem.steward.model.InvoicingConfig.class, InvoicingConfig.class ).byDefault().register();
        factory.classMap( biz.turnonline.ecosystem.steward.model.InvoicingConfigBillingAddress.class, InvoicingConfigBillingAddress.class ).byDefault().register();
        factory.classMap( biz.turnonline.ecosystem.steward.model.InvoicingConfigBillingContact.class, InvoicingConfigBillingContact.class ).byDefault().register();
        factory.classMap( biz.turnonline.ecosystem.steward.model.LegalForm.class, LegalForm.class ).byDefault().register();
        factory.classMap( biz.turnonline.ecosystem.steward.model.Logo.class, Logo.class ).byDefault().register();
        factory.classMap( biz.turnonline.ecosystem.steward.model.NewsletterSubscription.class, NewsletterSubscription.class ).byDefault().register();
        factory.classMap( biz.turnonline.ecosystem.steward.model.NewsletterResponse.class, NewsletterResponse.class ).byDefault().register();
    }
}
