package io.virtualcave.rates.application.commands;

import reactor.core.publisher.Mono;

public interface CommandReturnHandler<T, V> extends CommandHandler<T> {

  V executeAndReturn(T command);
}
