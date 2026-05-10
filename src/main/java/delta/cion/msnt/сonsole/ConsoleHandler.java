package delta.cion.msnt.сonsole;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ConsoleHandler {

	private final LineReader READER;

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleHandler.class);

	private static String LAST_INPUT = "";
	private static boolean CREATED;

	public ConsoleHandler() throws IOException, InterruptedException {
		Terminal TERMINAL = TerminalBuilder.builder().system(true).build();
		this.READER = LineReaderBuilder.builder().terminal(TERMINAL).build();
		Thread CONSOLE_THREAD = new Thread(this::run);
		if (isInit()) return;
		CONSOLE_THREAD.start();
	}

	private boolean isInit() {
		if (CREATED) {
			LOGGER.warn("Cannot create second ConsoleHandler instance.");
			return true;
		} else return false;
	}

	private void run() {
		while (true) {
			String line = this.READER.readLine();
			if (line.isEmpty()) continue;
			LOGGER.info("Console typed: /{}", line);
			saveInput(line);
		}
	}

	private static void stopConsoleReader() {

	}

	private static void saveInput(String string) {
		LAST_INPUT = string;
	}

	private static String getLastInput() {
		return LAST_INPUT;
	}

}
