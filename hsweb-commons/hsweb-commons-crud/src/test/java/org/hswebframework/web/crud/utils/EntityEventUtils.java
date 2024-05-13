package org.hswebframework.web.crud.utils;

import org.hswebframework.web.crud.events.EntityBeforeModifyEvent;
import org.hswebframework.web.crud.events.EntityCreatedEvent;
import org.hswebframework.web.crud.events.EntityModifyEvent;
import org.hswebframework.web.crud.events.EntityPrepareModifyEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class EntityEventUtils {

    public static <E> Mono<Void> onCreated(EntityCreatedEvent<E> event, Function<E, Mono<Void>> function) {
        return Flux.fromIterable(event.getEntity())
            .flatMap(function)
            .then();
    }

    public static <E> Mono<Void> onBeforeModify(EntityBeforeModifyEvent<E> event, BiConsumer<E, E> biFunction) {
        return Flux.fromIterable(event.getBefore())
            .flatMap(before -> Flux.fromIterable(event.getAfter())
                .doOnNext(after -> biFunction.accept(before, after)))
            .then();
    }

    public static <E> Mono<Void> onPrepareModifyEven(EntityPrepareModifyEvent<E> event, BiConsumer<E, E> biFunction) {
        return Flux.fromIterable(event.getBefore())
            .flatMap(before -> Flux.fromIterable(event.getAfter())
                .doOnNext(after -> biFunction.accept(before, after)))
            .then();
    }

    public static <E> Mono<Void> onModifyEven(EntityModifyEvent<E> event, BiFunction<E, E, Mono<Void>> biFunction) {
        return Flux.fromIterable(event.getBefore())
            .flatMap(before -> Flux.fromIterable(event.getAfter())
                .flatMap(after -> biFunction.apply(before, after)))
            .then();
    }

}
