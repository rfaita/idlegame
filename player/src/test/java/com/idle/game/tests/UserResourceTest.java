package com.idle.game.tests;

import com.idle.game.model.UserResource;
import com.idle.game.model.ResourceType;
import com.idle.game.server.service.UserResourceService;
import static com.idle.game.tests.helper.TestHelper.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.validation.ValidationException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import com.idle.game.server.repository.UserResourceRepository;

/**
 *
 * @author rafael
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserResourceTest {

    @MockBean
    private UserResourceRepository playerResourceRepository;

    @MockBean
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserResourceService playerResourceService;

    @Rule
    public ExpectedException expcetionExpect = ExpectedException.none();

    @Test
    public void testComputeResources1Second() {

        UserResource pr1 = createUserResource("3");

        pr1.setLastTimeResourcesCollected(Date.from(LocalDateTime.now().minus(1, ChronoUnit.SECONDS).atZone(ZoneId.systemDefault()).toInstant()));
        pr1.setLastTimeResourcesCollectedHour(pr1.getLastTimeResourcesCollected());
        pr1.setLastTimeResourcesCollectedDay(pr1.getLastTimeResourcesCollected());

        when(this.playerResourceRepository.findByUserId("3")).thenReturn(pr1);

        UserResource pr1Expected = playerResourceService.computeResources("3");

        Assert.assertEquals("3", pr1Expected.getUserId());

        Assert.assertEquals(new Long(0L), pr1Expected.getResource(ResourceType.RUNE).getValue());
        Assert.assertEquals(new Long(0L), pr1Expected.getResource(ResourceType.GOLD).getValue());
        Assert.assertEquals(new Long(0L), pr1Expected.getResource(ResourceType.ASHARD).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PS).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PS).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PS).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PH).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PH).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PH).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PD).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PD).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PD).getValue());

    }

    @Test
    public void testComputeResources3600Second() {

        UserResource pr1 = createUserResource("4");

        pr1.setLastTimeResourcesCollected(Date.from(LocalDateTime.now().minus(3600, ChronoUnit.SECONDS).atZone(ZoneId.systemDefault()).toInstant()));
        pr1.setLastTimeResourcesCollectedHour(pr1.getLastTimeResourcesCollected());
        pr1.setLastTimeResourcesCollectedDay(pr1.getLastTimeResourcesCollected());

        when(this.playerResourceRepository.findByUserId("4")).thenReturn(pr1);

        when(this.playerResourceRepository.save(any(UserResource.class))).thenAnswer(createUserResourceAnswerForSomeInput());

        UserResource pr1Expected = playerResourceService.computeResources("4");

        Assert.assertEquals("4", pr1Expected.getUserId());

        Assert.assertEquals(new Long(36010L), pr1Expected.getResource(ResourceType.RUNE).getValue());
        Assert.assertEquals(new Long(360100L), pr1Expected.getResource(ResourceType.GOLD).getValue());
        Assert.assertEquals(new Long(3601L), pr1Expected.getResource(ResourceType.ASHARD).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PS).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PS).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PS).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PH).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PH).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PH).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PD).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PD).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PD).getValue());

    }

    @Test
    public void testComputeResources4200Second() {

        UserResource pr1 = createUserResource("4");

        pr1.setLastTimeResourcesCollected(Date.from(LocalDateTime.now().minus(4200, ChronoUnit.SECONDS).atZone(ZoneId.systemDefault()).toInstant()));
        pr1.setLastTimeResourcesCollectedHour(pr1.getLastTimeResourcesCollected());
        pr1.setLastTimeResourcesCollectedDay(pr1.getLastTimeResourcesCollected());

        when(this.playerResourceRepository.findByUserId("4")).thenReturn(pr1);

        when(this.playerResourceRepository.save(any(UserResource.class))).thenAnswer(createUserResourceAnswerForSomeInput());

        UserResource pr1Expected = playerResourceService.computeResources("4");

        Assert.assertEquals("4", pr1Expected.getUserId());

        Assert.assertEquals(new Long(42010L), pr1Expected.getResource(ResourceType.RUNE).getValue());
        Assert.assertEquals(new Long(420100L), pr1Expected.getResource(ResourceType.GOLD).getValue());
        Assert.assertEquals(new Long(4201L), pr1Expected.getResource(ResourceType.ASHARD).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PS).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PS).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PS).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PH).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PH).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PH).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PD).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PD).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PD).getValue());

    }

    @Test
    public void testComputeResources86400Second() {

        UserResource pr1 = createUserResource("4");

        pr1.setLastTimeResourcesCollected(Date.from(LocalDateTime.now().minus(86400, ChronoUnit.SECONDS).atZone(ZoneId.systemDefault()).toInstant()));
        pr1.setLastTimeResourcesCollectedHour(pr1.getLastTimeResourcesCollected());
        pr1.setLastTimeResourcesCollectedDay(pr1.getLastTimeResourcesCollected());

        when(this.playerResourceRepository.findByUserId("4")).thenReturn(pr1);

        when(this.playerResourceRepository.save(any(UserResource.class))).thenAnswer(createUserResourceAnswerForSomeInput());

        UserResource pr1Expected = playerResourceService.computeResources("4");

        Assert.assertEquals("4", pr1Expected.getUserId());

        Assert.assertEquals(new Long(288090L), pr1Expected.getResource(ResourceType.RUNE).getValue());
        Assert.assertEquals(new Long(2880900L), pr1Expected.getResource(ResourceType.GOLD).getValue());
        Assert.assertEquals(new Long(28809L), pr1Expected.getResource(ResourceType.ASHARD).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PS).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PS).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PS).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PH).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PH).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PH).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PD).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PD).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PD).getValue());

    }

    @Test
    public void testComputeResources100000Second() {

        UserResource pr1 = createUserResource("4");

        pr1.setLastTimeResourcesCollected(Date.from(LocalDateTime.now().minus(100000, ChronoUnit.SECONDS).atZone(ZoneId.systemDefault()).toInstant()));
        pr1.setLastTimeResourcesCollectedHour(pr1.getLastTimeResourcesCollected());
        pr1.setLastTimeResourcesCollectedDay(pr1.getLastTimeResourcesCollected());

        when(this.playerResourceRepository.findByUserId("4")).thenReturn(pr1);

        when(this.playerResourceRepository.save(any(UserResource.class))).thenAnswer(createUserResourceAnswerForSomeInput());

        UserResource pr1Expected = playerResourceService.computeResources("4");

        Assert.assertEquals("4", pr1Expected.getUserId());

        Assert.assertEquals(new Long(288090L), pr1Expected.getResource(ResourceType.RUNE).getValue());
        Assert.assertEquals(new Long(2880900L), pr1Expected.getResource(ResourceType.GOLD).getValue());
        Assert.assertEquals(new Long(28809L), pr1Expected.getResource(ResourceType.ASHARD).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PS).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PS).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PS).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PH).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PH).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PH).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PD).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PD).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PD).getValue());

    }
    @Test
    public void testComputeResources216000Second() {

        UserResource pr1 = createUserResource("4");

        pr1.setLastTimeResourcesCollected(Date.from(LocalDateTime.now().minus(216000, ChronoUnit.SECONDS).atZone(ZoneId.systemDefault()).toInstant()));
        pr1.setLastTimeResourcesCollectedHour(pr1.getLastTimeResourcesCollected());
        pr1.setLastTimeResourcesCollectedDay(pr1.getLastTimeResourcesCollected());

        when(this.playerResourceRepository.findByUserId("4")).thenReturn(pr1);

        when(this.playerResourceRepository.save(any(UserResource.class))).thenAnswer(createUserResourceAnswerForSomeInput());

        UserResource pr1Expected = playerResourceService.computeResources("4");

        Assert.assertEquals("4", pr1Expected.getUserId());

        Assert.assertEquals(new Long(288100L), pr1Expected.getResource(ResourceType.RUNE).getValue());
        Assert.assertEquals(new Long(2881000L), pr1Expected.getResource(ResourceType.GOLD).getValue());
        Assert.assertEquals(new Long(28810L), pr1Expected.getResource(ResourceType.ASHARD).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PS).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PS).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PS).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PH).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PH).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PH).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PD).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PD).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PD).getValue());

    }

    @Test
    public void testComputeResources10Second() {

        UserResource pr1 = createUserResource("4");

        pr1.setLastTimeResourcesCollected(Date.from(LocalDateTime.now().minus(10, ChronoUnit.SECONDS).atZone(ZoneId.systemDefault()).toInstant()));
        pr1.setLastTimeResourcesCollectedHour(pr1.getLastTimeResourcesCollected());
        pr1.setLastTimeResourcesCollectedDay(pr1.getLastTimeResourcesCollected());

        when(this.playerResourceRepository.findByUserId("4")).thenReturn(pr1);

        when(this.playerResourceRepository.save(any(UserResource.class))).thenAnswer(createUserResourceAnswerForSomeInput());

        UserResource pr1Expected = playerResourceService.computeResources("4");

        Assert.assertEquals("4", pr1Expected.getUserId());

        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.RUNE).getValue());
        Assert.assertEquals(new Long(1000L), pr1Expected.getResource(ResourceType.GOLD).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.ASHARD).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PS).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PS).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PS).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PH).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PH).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PH).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PD).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PD).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PD).getValue());

    }

    @Test
    public void testComputeResources5Second() {

        UserResource pr1 = createUserResource("5");

        pr1.setLastTimeResourcesCollected(Date.from(LocalDateTime.now().minus(5, ChronoUnit.SECONDS).atZone(ZoneId.systemDefault()).toInstant()));
        pr1.setLastTimeResourcesCollectedHour(pr1.getLastTimeResourcesCollected());
        pr1.setLastTimeResourcesCollectedDay(pr1.getLastTimeResourcesCollected());

        when(this.playerResourceRepository.findByUserId("5")).thenReturn(pr1);

        when(this.playerResourceRepository.save(any(UserResource.class))).thenAnswer(createUserResourceAnswerForSomeInput());

        UserResource pr1Expected = playerResourceService.computeResources("5");

        Assert.assertEquals("5", pr1Expected.getUserId());

        Assert.assertEquals(new Long(50L), pr1Expected.getResource(ResourceType.RUNE).getValue());
        Assert.assertEquals(new Long(500L), pr1Expected.getResource(ResourceType.GOLD).getValue());
        Assert.assertEquals(new Long(5L), pr1Expected.getResource(ResourceType.ASHARD).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PS).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PS).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PS).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PH).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PH).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PH).getValue());
        Assert.assertEquals(new Long(10L), pr1Expected.getResource(ResourceType.RUNE_PD).getValue());
        Assert.assertEquals(new Long(100L), pr1Expected.getResource(ResourceType.GOLD_PD).getValue());
        Assert.assertEquals(new Long(1L), pr1Expected.getResource(ResourceType.ASHARD_PD).getValue());

    }

    @Test
    public void testUseResourcesWithHaveValue() {

        doNothing().when(this.simpMessagingTemplate).convertAndSend(any(String.class), any(UserResource.class));

        when(this.playerResourceRepository.findByUserId("6")).thenReturn(createUserResourceWith100OfEachResources("6"));

        when(this.playerResourceRepository.save(any(UserResource.class))).thenAnswer(createUserResourceAnswerForSomeInput());

        UserResource pr1Expected = playerResourceService.useResources("6", create50OfEachResources());

        Assert.assertEquals("6", pr1Expected.getUserId());

        Assert.assertEquals(new Long(50L), pr1Expected.getResource(ResourceType.RUNE).getValue());
        Assert.assertEquals(new Long(50L), pr1Expected.getResource(ResourceType.GOLD).getValue());
        Assert.assertEquals(new Long(50L), pr1Expected.getResource(ResourceType.ASHARD).getValue());

    }

    @Test
    public void testUseResourcesWithAllHaveValue() {

        doNothing().when(this.simpMessagingTemplate).convertAndSend(any(String.class), any(UserResource.class));

        when(this.playerResourceRepository.findByUserId("7")).thenReturn(createUserResourceWith50OfEachResources("7"));

        when(this.playerResourceRepository.save(any(UserResource.class))).thenAnswer(createUserResourceAnswerForSomeInput());

        UserResource pr1Expected = playerResourceService.useResources("7", create50OfEachResources());

        Assert.assertEquals("7", pr1Expected.getUserId());

        Assert.assertEquals(new Long(0L), pr1Expected.getResource(ResourceType.RUNE).getValue());
        Assert.assertEquals(new Long(0L), pr1Expected.getResource(ResourceType.GOLD).getValue());
        Assert.assertEquals(new Long(0L), pr1Expected.getResource(ResourceType.ASHARD).getValue());

    }

    @Test
    public void testUseResourcesWithMoreHaveValue() {

        doNothing().when(this.simpMessagingTemplate).convertAndSend(any(String.class), any(UserResource.class));

        when(this.playerResourceRepository.findByUserId("8")).thenReturn(createUserResourceWith50OfEachResources("8"));

        when(this.playerResourceRepository.save(any(UserResource.class))).thenAnswer(createUserResourceAnswerForSomeInput());

        expcetionExpect.expect(ValidationException.class);
        expcetionExpect.expectMessage("using.more.resource.you.have");

        playerResourceService.useResources("8", create100OfEachResources());

    }

}
