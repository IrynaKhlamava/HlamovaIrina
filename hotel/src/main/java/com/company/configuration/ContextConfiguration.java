package com.company.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan({"com.company.dao", "com.company.service", "com.company.configuration", "com.company.ui.menu", "com" +
        ".company.facade"})
@EnableTransactionManagement
public class ContextConfiguration {
}
