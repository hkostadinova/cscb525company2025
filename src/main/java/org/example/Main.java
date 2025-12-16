package org.example;

import org.example.dao.CompanyDao;
import org.example.entity.Company;

public class Main {
    public static void main(String[] args) {

        CompanyDao.companiesDtoFindByInitialCapitalBetween(7500, 10000)
                        .forEach(System.out::println);


        CompanyDao.companiesFindByInitialCapitalBetween(7500,10000)
                .forEach(System.out::println);

        CompanyDao.findByNameStartingWithAndInitialCapitalGreaterThan("ib",10000)
                .forEach(System.out::println);

        System.out.println(CompanyDao.sumInitialCapital());

        Company company = new Company();
        company.setName("Looooooooooooooooooooooong");
        CompanyDao.createCompany(company);

    }
}