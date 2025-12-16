package org.example.dao;

import jakarta.validation.*;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Company;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CompanyDaoTest {

    private static SessionFactory sessionFactory;

    @BeforeAll
    static void setup() {
        sessionFactory = SessionFactoryUtil.getSessionFactory();
    }

    @AfterAll
    static void close() {
        sessionFactory.close();
    }

    @BeforeEach
    void initTests() {
        sessionFactory.getSchemaManager().truncateMappedObjects();
    }

    @AfterEach
    void endTests() {
        sessionFactory.getSchemaManager().truncateMappedObjects();
    }

    @Test
    public void givenCompany_whenSave_thenGetOk() {
        Company company = Company.builder()
                .name("Name")
                .initialCapital(BigDecimal.valueOf(8000))
                .foundationDate(LocalDate.of(2002, 6, 6))
                .build();

        CompanyDao.createCompany(company);

        List<Company> companies = CompanyDao.getCompanies();
        assertEquals("Name", companies.getFirst().getName());
        assertEquals(new BigDecimal("8000.00"), companies.getFirst().getInitialCapital());
    }

    @Test
    public void whenNameStartsWithSmallLetter_thenConstraintViolationEx() {
        Company company = Company.builder()
                .name("name")
                .build();
        assertThrows(ConstraintViolationException.class, () -> CompanyDao.createCompany(company));
    }

    private List<String> validate(Company company) {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();

            return validator.validate(company)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
        }
    }

    @Test
    public void whenInvalidCompanyNameStartsWithSmallLetter_thenAssertConstraintViolation() {
        Company company = Company.builder()
                .name("name")
                .build();

        assertThrows(ConstraintViolationException.class, () -> CompanyDao.createCompany(company));
    }

    @Test
    public void whenInvalidCompanyNameStartsWithSmallLetter_thenAssertConstraintViolations() {
        Company company = Company.builder()
                .name("name")
                .build();

        List<String> messages = validate(company);

        assertEquals(1, messages.size());
        assertEquals("Company name has to start with capital letter!", messages.getFirst());
    }

    @Test
    public void whenInvalidCompanyName_thenAssertConstraintViolations() {
        Company company = Company.builder()
                .name("Company")
                .build();

        List<String> messages = validate(company);

        assertEquals(1, messages.size());
        assertEquals("The name is not valid!", messages.getFirst());
    }

    @Test
    public void whenInvalidCompanyInitialCapital_thenAssertConstraintViolations() {
        Company company = Company.builder()
                .name("Name")
                .initialCapital(BigDecimal.valueOf(10500))
                .build();

        List<String> messages = validate(company);

        assertEquals(1, messages.size());
        assertEquals("Initial capital has to be less than or equal to 10000.00", messages.getFirst());
    }
}