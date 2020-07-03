package org.itxtech.nemisys.plugin.certification;

import java.io.Serializable;


public abstract class PluginCertificate extends Thread implements Serializable {

    protected transient boolean localCertificated = false;

    protected String certificate;
    protected boolean certificated;
    protected String encryptType;

}
