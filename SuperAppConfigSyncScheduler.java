
@Component(immediate = true, service = SuperAppConfigSyncScheduler.class)
@Designate(ocd = SuperAppConfigSyncConfiguration.class)
public class SuperAppConfigSyncScheduler implements Runnable {
    @Reference
    private Scheduler scheduler;

    private int schedulerID;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String customParameter = "";

    @Activate
    protected void activate(SuperAppConfigSyncConfiguration config) {
        schedulerID = config.schedulerName().hashCode();
        customParameter = config.customPathParameter();
    }

    @Modified
    protected void modified(SuperAppConfigSyncConfiguration config) {
        removeScheduler();
        schedulerID = config.schedulerName().hashCode(); // update schedulerID
        customParameter = config.customPathParameter();
        addScheduler(config);
    }

    @Deactivate
    protected void deactivate(SuperAppConfigSyncConfiguration config) {
        removeScheduler();
    }

    /**
     * Remove a scheduler based on the scheduler ID
     */
    private void removeScheduler() {
        logger.debug("Removing Scheduler Job '{}'", schedulerID);
        scheduler.unschedule(String.valueOf(schedulerID));
    }

    /**
     * Add a scheduler based on the scheduler ID
     */
    private void addScheduler(SuperAppConfigSyncConfiguration config) {
        if (config.enabled()) {
            ScheduleOptions sopts = scheduler.EXPR(config.cronExpression());
            sopts.name(String.valueOf(schedulerID));
            sopts.canRunConcurrently(false);
            scheduler.schedule(this, sopts);
            logger.debug("Scheduler added successfully");
        } else {
            logger.debug("SuperAppConfigSyncScheduler is Disabled, no scheduler job created");
        }
    }

    @Override
    public void run() {
        if (StringUtils.isEmpty(customParameter)) {
            return;
        }

   /*to do*/
    }

}
