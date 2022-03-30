package io.virtualcave.rates.application.queries;

public interface QueryHandler<T extends Query, R> {
  R execute(T query);
}
