package org.bluett.service;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Log4j2
public class CommandService {
    public void executeCommand(String[] command) {
        try {
            // 创建 ProcessBuilder 对象
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            // 启动进程
            Process process = processBuilder.start();
            // 读取进程的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                log.debug(line);
            }
            // 等待命令执行完毕
            int exitCode = process.waitFor();
            log.debug("Exit code: {}", exitCode);
        } catch (IOException | InterruptedException e) {
            log.error("Error in executeCommand:", ExceptionUtils.getRootCause(e));
        }
    }
}
