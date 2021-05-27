package com.fetnet.portal.aem.cbu.internal.schedulers.osgi;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * @author Anirudh Sharma
 * <p>
 * This is the configuration class that takes properties for a scheduler to run
 */
@ObjectClassDefinition(name = "Sync SuperApp Config to publisher", description = "Config sync scheduler")
public @interface SuperAppConfigSyncConfiguration {

    /**
     * This method will return the name of the Scheduler
     *
     * @return {@link String}
     */
    @AttributeDefinition(
        name = "Scheduler name",
        description = "Name of the scheduler",
        type = AttributeType.STRING)
    public String schedulerName() default "SuperApp Config Scheduler Configuration";

    /**
     * This method will check if the scheduler is concurrent or not
     *
     * @return {@link Boolean}
     */
    @AttributeDefinition(
        name = "Enabled",
        description = "True, if scheduler service is enabled",
        type = AttributeType.BOOLEAN)
    public boolean enabled() default false;

    /**
     * This method returns the Cron expression which will decide how the scheduler will run
     *
     * @return {@link String}
     */
    @AttributeDefinition(
        name = "Cron Expression",
        description = "Cron expression used by the scheduler s m h d M",
        type = AttributeType.STRING)
    public String cronExpression() default "10 * * * * ?";

    /**
     * This method returns a custom parameter just to show case the functionality
     *
     * @return {@link String}
     */
    @AttributeDefinition(
        name = "Replicated Path",
        description = "Replicated path to be publish by the scheduler",
        type = AttributeType.STRING)
    public String customPathParameter() default "";
}
