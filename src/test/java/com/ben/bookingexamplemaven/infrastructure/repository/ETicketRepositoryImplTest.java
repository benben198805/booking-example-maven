package com.ben.bookingexamplemaven.infrastructure.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.ben.bookingexamplemaven.domain.model.ETicket;
import com.ben.bookingexamplemaven.domain.model.enums.ETicketStatus;
import com.ben.bookingexamplemaven.infrastructure.dataentity.ETicketEntity;
import com.ben.bookingexamplemaven.infrastructure.persistence.ETicketJpaPersistence;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ETicketRepositoryImplTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ETicketJpaPersistence persistence;

    private ETicketRepositoryImpl repository;

    @Before
    public void setUp() {
        repository = new ETicketRepositoryImpl(persistence);
    }

    @Test
    public void should_create_eTicket() {
        // given
        ETicket eTicket = ETicket.builder().status(ETicketStatus.PENDING).build();

        // when
        ETicket result = this.repository.create(eTicket);

        // then
        ETicketEntity eTicketEntity = entityManager.getEntityManager().find(ETicketEntity.class, result.getId());
        assertEquals(ETicketStatus.PENDING, eTicketEntity.getStatus());
    }
}
