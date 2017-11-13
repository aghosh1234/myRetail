package com.retail.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * The service configuration object to be used by all REST services.  
 */
@Component("serviceConfiguration")
public class ServiceConfiguration extends AbstractServiceConfiguration{
    private static Logger LOG = LoggerFactory.getLogger(ServiceConfiguration.class);

    private Environment env;

    @Autowired
    public ServiceConfiguration(Environment env) {
        this.env = env;
        LOG.info("Service configuration has been created.");
    }

    public static String convertValueToString(Object value) {
        if(value==null) {
            return null;
        } else if(value instanceof String) {
            return (String)value;
        } else if(value instanceof Integer) {
            return Integer.toString((Integer)value);
        } else if(value instanceof Float) {
            return Float.toString((Float)value);
        } else if(value instanceof Double) {
            return Double.toString((Double)value);
        } else if(value instanceof Boolean) {
            return Boolean.toString((Boolean)value);
        } else if(value instanceof Long) {
            return Long.toString((Long) value);
        } else {
            try {
                LOG.warn("Unknown property type. No specific conversion handler found: {}", value.getClass());
                return value.toString();
            } catch (Throwable thrown) {
                LOG.error("Failed to convert unknown property type to string.",value.getClass());
                return null;
            }
        }
    }

    @Override
    public String getPropertyValue(String name) {
        return this.env.getProperty(name);
    }

    public boolean contains(String name) {
        return this.env.containsProperty(name);
    }
    
    public boolean containsValue(String name) {
        return this.env.getProperty(name)!=null;
    }
    
   
    /**
     * Convenience method for getting an Integer property value from a {@link AbstractServiceConfiguration} implementation, and which also checks if the configuration object is <code>null</code>.
     * @param configuration The configuration object to extract the value from.
     * @param key The value key.
     * @param defaultVal The default value to be returned if the configuration object is <code>null</code> or no value is found in the configuration object.
     * @return The key value if one exists in the configuration, or the default value if either the configuration object is <code>null</code> or no value for the key exists in the configuration. 
     */
    public static Integer extractInteger(AbstractServiceConfiguration configuration, String key, Integer defaultVal) {
        return (configuration!=null ? configuration.getInteger(key,defaultVal) : defaultVal);
    }

    /**
     * Convenience method for getting an String property value from a {@link AbstractServiceConfiguration} implementation, and which also checks if the configuration object is <code>null</code>.
     * @param configuration The configuration object to extract the value from.
     * @param key The value key.
     * @param defaultVal The default value to be returned if the configuration object is <code>null</code> or no value is found in the configuration object.
     * @return The key value if one exists in the configuration, or the default value if either the configuration object is <code>null</code> or no value for the key exists in the configuration. 
     */
    public static String extractString(AbstractServiceConfiguration configuration, String key, String defaultVal) {
        return (configuration!=null ? configuration.getString(key,defaultVal) : defaultVal);
    }

    /**
     * Convenience method for getting an Boolean property value from a {@link AbstractServiceConfiguration} implementation, and which also checks if the configuration object is <code>null</code>.
     * @param configuration The configuration object to extract the value from.
     * @param key The value key.
     * @param defaultVal The default value to be returned if the configuration object is <code>null</code> or no value is found in the configuration object.
     * @return The key value if one exists in the configuration, or the default value if either the configuration object is <code>null</code> or no value for the key exists in the configuration. 
     */
    public static Boolean extractBoolean(AbstractServiceConfiguration configuration, String key, Boolean defaultVal) {
        return (configuration!=null ? configuration.getBoolean(key,defaultVal) : defaultVal);
    }

    /**
     * Convenience method for getting an Double property value from a {@link AbstractServiceConfiguration} implementation, and which also checks if the configuration object is <code>null</code>.
     * @param configuration The configuration object to extract the value from.
     * @param key The value key.
     * @param defaultVal The default value to be returned if the configuration object is <code>null</code> or no value is found in the configuration object.
     * @return The key value if one exists in the configuration, or the default value if either the configuration object is <code>null</code> or no value for the key exists in the configuration. 
     */
    public static Double extractDouble(AbstractServiceConfiguration configuration, String key, Double defaultVal) {
        return (configuration!=null ? configuration.getDouble(key,defaultVal) : defaultVal);
    }

    /**
     * Convenience method for getting an Float property value from a {@link AbstractServiceConfiguration} implementation, and which also checks if the configuration object is <code>null</code>.
     * @param configuration The configuration object to extract the value from.
     * @param key The value key.
     * @param defaultVal The default value to be returned if the configuration object is <code>null</code> or no value is found in the configuration object.
     * @return The key value if one exists in the configuration, or the default value if either the configuration object is <code>null</code> or no value for the key exists in the configuration. 
     */
    public static Float extractFloat(AbstractServiceConfiguration configuration, String key, Float defaultVal) {
        return (configuration!=null ? configuration.getFloat(key,defaultVal) : defaultVal);
    }

    /**
     * Convenience method for getting an Long property value from a {@link AbstractServiceConfiguration} implementation, and which also checks if the configuration object is <code>null</code>.
     * @param configuration The configuration object to extract the value from.
     * @param key The value key.
     * @param defaultVal The default value to be returned if the configuration object is <code>null</code> or no value is found in the configuration object.
     * @return The key value if one exists in the configuration, or the default value if either the configuration object is <code>null</code> or no value for the key exists in the configuration. 
     */
    public static Long extractLong(AbstractServiceConfiguration configuration, String key, Long defaultVal) {
        return (configuration!=null ? configuration.getLong(key,defaultVal) : defaultVal);
    }


}
