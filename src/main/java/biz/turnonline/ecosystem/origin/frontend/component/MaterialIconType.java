package biz.turnonline.ecosystem.origin.frontend.component;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;

/**
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
public class MaterialIconType extends IconType
{
    public static final MaterialIconType lockOpen = new MaterialIconType("lock_open");
    public static final MaterialIconType edit = new MaterialIconType("edit");
    public static final MaterialIconType person = new MaterialIconType("person");
    public static final MaterialIconType powerSettingsNew = new MaterialIconType("power_settings_new");


    public MaterialIconType( String tagBody )
    {
        super(tagBody, tagBody );
    }

    @Override
    public String cssClassName()
    {
        return "material-icons";
    }
}
