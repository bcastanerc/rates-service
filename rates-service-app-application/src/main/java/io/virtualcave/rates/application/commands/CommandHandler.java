package io.virtualcave.rates.application.commands;

import reactor.core.publisher.Mono;

public interface CommandHandler<T> {

  void execute(T command);
}
