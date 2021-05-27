# aem-schedule
How to create schedule in aem

主要分成兩個部分:
 1.OSGi configuration 
	目的: 在web console 上可以設定執行所需要的參數。
 2.Scheduler
	目的:
		a.接OSGi的參數。設定時、參數修改時的偵測。
		b.schedule 本身要做的事情。
		

 1.OSGi configuration :
 
 ref : https://aem.redquark.org/2018/10/day-12-creating-your-custom-osgi.html
 
 主要幾個annotation
 @ObjectClassDefinition(name="string",description="string")
 name: webconsole 上所要顯示的名字
 description : 簡介
 
 
  @AttributeDefinition(
        name = "Scheduler name",
        description = "Name of the scheduler",
        type = AttributeType.STRING)
 public String schedulerName() default "SuperApp Config Scheduler Configuration";
 
 其實就是osgi 內要給人填的入的選項。
 
 How to use osgi parameter?
@Designate(ocd = SuperAppConfigSyncConfiguration.class)

使用這個annotation
告訴程式說，我要從這邊取得osgi para。

 2.Scheduler:
 package org.apache.sling.commons.scheduler.Scheduler 
 
 How scheduler work?
 目標就是這句
 schedule(Object job, ScheduleOptions options)
 ScheduleOptions 從 schedule.exp(執行時間)
 ScheduleOptions sopts = scheduler.EXPR(config.cronExpression());
   sopts.name(String.valueOf(schedulerID));
   sopts.canRunConcurrently(false);
 其他都是在做蒐集資料的部分。
 稍微講講
 @Activate 在osgi綁上去的時執行(init時)。
 @Modified 再次修改osgi時會被執行(打開在按下save)。
 
 
 
 
 *************************那些我曾踩過的坑***************************
 天真的我以為osgi歸osgi 寫完第一part編譯完預期看到osgi寫在webconsole上 很抱歉並沒有。
 搭配@Designate(ocd = xxxConfiguration.class) 才會出現。
 
 cronExpression "10 * * * * ?";"秒 分 時 天 月"
 意思是: 任何天任何時任合分的10s執行 
 
 
 
 ****************************ref***************************************
 https://sling.apache.org/documentation/bundles/scheduler-service-commons-scheduler.html
 https://sling.apache.org/apidocs/sling9/org/apache/sling/commons/scheduler/Scheduler.html
 https://aem.redquark.org/2018/10/day-12-creating-your-custom-osgi.html
 https://aem.redquark.org/2018/10/day-13-schedulers-in-aem.html
 
 cron https://matthung0807.blogspot.com/2020/12/quartz-cron-expressions-format.html
