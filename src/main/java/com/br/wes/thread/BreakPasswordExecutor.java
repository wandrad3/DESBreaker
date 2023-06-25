package com.br.wes.thread;

import java.util.concurrent.*;

public class BreakPasswordExecutor {

	public boolean passwordWasBreak(String user, int lentgh) {
		ExecutorService executorA = null;
		ExecutorService executorB = null;
		ExecutorService executorC = null;
		try {
			executorA = Executors.newFixedThreadPool(300);
			executorB = Executors.newFixedThreadPool(300);
			executorC = Executors.newFixedThreadPool(300);

			Future<Boolean> futureA = executorA.submit(new BreakPasswordProcessorContextA(user, lentgh));

			boolean resultA = futureA.get();
			if (resultA) {
				return true;
			}

			Future<Boolean> futureB = executorB.submit(new BreakPasswordProcessorContextB(user, lentgh));
			boolean resultB = futureB.get();

			if (resultB) {
				return true;

			}

			Future<Boolean> futureC = executorC.submit(new BreakPasswordProcessorContextC(user,lentgh));
			boolean resultC = futureC.get();

			return resultC;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (executorA != null) {
				executorA.shutdown();
			}
			if (executorB != null) {
				executorB.shutdown();
			}
			if (executorC != null) {
				executorC.shutdown();
			}
		}
	}
}
