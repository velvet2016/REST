package com.lux.task.dao.impl;

import com.lux.task.dao.models.Product;
import com.lux.task.dao.models.Purchase;
import com.lux.task.dao.models.ReportLine;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
@WebAppConfiguration
@Transactional
public class ReportDaoImplTest extends AbstractDaoTest {

    @Rollback
    @Test
    public void getReportFreshPurchaseIsInReportTest() throws Exception {

        //arrange - creating purchase with date "now"
        int monthCount = 1;
        Product testProduct = getTestProductAndSaveIt();
        Purchase purchase = new Purchase(testProduct, 1, new Date());

        //act - save purchase to DB, getting report for 1  month
        purchaseDao.save(purchase);
        List<ReportLine> report = reportDao.getReport(monthCount);
        ReportLine reportLine = new ReportLine(purchase);

        //assert - check that report for last month contains purchase we stored
        assertEquals(1, Collections.frequency(report, reportLine));
    }
    @Rollback
    @Test
    public void getReportOldPurchaseIsNotInReportTest() throws Exception {

        //arrange - creating purchase with date "one month ago"
        int monthCount = 1;
        Product testProduct = getTestProductAndSaveIt();
        Purchase purchase = new Purchase(testProduct, 1, DateUtils.addMonths(new Date(),-1));

        //act - save purchase to DB, getting report for 1  month
        purchaseDao.save(purchase);
        List<ReportLine> report = reportDao.getReport(monthCount);

        //assert - check that report for last month does not contain purchase we stored
        ReportLine reportLine = new ReportLine(purchase);
        assertFalse(report.contains(reportLine));
    }
}
