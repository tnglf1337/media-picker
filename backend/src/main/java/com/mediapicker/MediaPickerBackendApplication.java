package com.mediapicker;

import com.mediapicker.web.OfflineAppRegister;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MediaPickerBackendApplication {

  private final OfflineAppRegister offlineAppRegister;

  public MediaPickerBackendApplication(OfflineAppRegister offlineAppRegister) {
    this.offlineAppRegister = offlineAppRegister;
  }

  public static void main(String[] args) {
    SpringApplication.run(MediaPickerBackendApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {
      this.offlineAppRegister.registriereDefaultUser();
    };
  }

}
