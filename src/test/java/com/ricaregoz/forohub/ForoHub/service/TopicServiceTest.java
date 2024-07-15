package com.dsanchez.forohub.ForoHub.service;

import com.dsanchez.forohub.ForoHub.domain.topic.Topic;
import com.dsanchez.forohub.ForoHub.domain.topic.TopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TopicServiceTest {

    @Mock
    private TopicRepository topicRepository;

    @InjectMocks
    private TopicService topicService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTopic() {
        Topic topic = new Topic();
        topic.setTitle("Nuevo Tópico");
        topic.setMessage("Este es el contenido del nuevo tópico.");
        topic.setCourseName("Java");

        User user = mock(User.class);
        when(user.getUsername()).thenReturn("user@example.com");

        when(topicRepository.save(any(Topic.class))).thenReturn(topic);

        Topic createdTopic = topicService.createTopic(topic, user);

        assertNotNull(createdTopic);
        assertEquals("Nuevo Tópico", createdTopic.getTitle());
        assertEquals("user@example.com", createdTopic.getUserEmail());
    }

    @Test
    void testGetAllTopics() {
        when(topicRepository.findAll()).thenReturn(List.of(new Topic(), new Topic()));

        List<Topic> topics = topicService.getAllTopics();

        assertEquals(2, topics.size());
    }

    @Test
    void testDeleteTopic() {
        Long topicId = 1L;
        topicService.deleteTopic(topicId);
        verify(topicRepository, times(1)).deleteById(topicId);
    }

    @Test
    void testUpdateTopic() {
        Long topicId = 1L;
        Topic topic = new Topic();
        topic.setTitle("Updated Title");
        topic.setMessage("Updated Message");
        topic.setCourseName("Updated Course");

        Topic existingTopic = new Topic();
        existingTopic.setId(topicId);

        when(topicRepository.findById(topicId)).thenReturn(Optional.of(existingTopic));
        when(topicRepository.save(existingTopic)).thenReturn(existingTopic);

        Topic updatedTopic = topicService.updateTopic(topicId, topic);

        assertEquals("Updated Title", updatedTopic.getTitle());
        assertEquals("Updated Message", updatedTopic.getMessage());
        assertEquals("Updated Course", updatedTopic.getCourseName());
    }
}