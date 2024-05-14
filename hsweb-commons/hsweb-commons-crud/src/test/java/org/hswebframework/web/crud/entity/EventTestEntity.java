package org.hswebframework.web.crud.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hswebframework.web.api.crud.entity.GenericEntity;
import org.hswebframework.web.crud.annotation.EnableEntityEvent;
import org.hswebframework.web.crud.generator.Generators;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "s_test_event")
@AllArgsConstructor
@NoArgsConstructor
@EnableEntityEvent
public class EventTestEntity extends GenericEntity<String> {

    @Column(length = 32)
    private String name;

    @Column
    private Integer age;

    @Column
    private Long testColumn;

    @Column
    private Integer testColumn2;

    @Override
    @GeneratedValue(generator = Generators.DEFAULT_ID_GENERATOR)
    public String getId() {
        return super.getId();
    }

    public static EventTestEntity of(String name, Integer age) {
        EventTestEntity eventTestEntity = new EventTestEntity();
        eventTestEntity.setName(name);
        eventTestEntity.setAge(age);
        return eventTestEntity;
    }
}
