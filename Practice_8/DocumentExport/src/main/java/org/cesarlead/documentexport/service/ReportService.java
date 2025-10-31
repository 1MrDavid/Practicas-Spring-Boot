package org.cesarlead.documentexport.service;

import org.cesarlead.documentexport.dto.AccountSummaryDTO;
import org.cesarlead.documentexport.dto.BankReportDTO;
import org.cesarlead.documentexport.dto.CustomerDTO;
import org.cesarlead.documentexport.dto.TransactionDTO;
import org.cesarlead.documentexport.model.Account;
import org.cesarlead.documentexport.model.Transaction;

public interface ReportService {
    BankReportDTO generateCustomerReport(Long customerId);
}
