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
    private Date now = DateUtils.truncate(new Date(), Calendar.DATE);

    @Rollback
    @Test
    public void getReportFreshPurchaseIsInReportTest() throws Exception {
        int monthCount = 1;
        Product testProduct = getTestProductAndSaveIt();
        Purchase purchase = new Purchase(testProduct, 1, now);
        purchaseDao.save(purchase);
        List<ReportLine> report = reportDao.getReport(monthCount);
        ReportLine reportLine = new ReportLine(purchase);
        assertEquals(1, Collections.frequency(report, reportLine));
    }
    @Rollback
    @Test
    public void getReportOldPurchaseIsNotInReportTest() throws Exception {
        int monthCount = 1;
        Product testProduct = getTestProductAndSaveIt();
        Purchase purchase = new Purchase(testProduct, 1, DateUtils.addDays(now,-29));
        purchaseDao.save(purchase);
        List<ReportLine> report = reportDao.getReport(monthCount);
        ReportLine reportLine = new ReportLine(purchase);
        assertFalse(report.contains(reportLine));
    }
}
