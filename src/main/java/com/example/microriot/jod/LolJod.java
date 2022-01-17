package com.example.microriot.jod;

import com.example.microriot.model.Config;
import com.example.microriot.model.ConfigUrl;
import com.example.microriot.model.base.Status;
import com.example.microriot.model.entity.ChallengerLeagues;
import com.example.microriot.model.request.ConfigSettingJodRequest;
import com.example.microriot.model.response.ConfigSettingJodResponse;
import com.example.microriot.repository.ChallengerLeaguesRepository;
import com.example.microriot.utils.ConfigUtils;
import com.example.microriot.utils.ConnectorClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Log4j2
@CrossOrigin
@RestController
@RequestMapping(value = "/riot/league/api/v1")
public class LolJod {
    @Autowired
    ChallengerLeaguesRepository challengerLeaguesRepository;

    @Autowired
    private ConnectorClient connectorClient;

    private String XRiotToken = "";
    private List<ConfigUrl> configUrlList = new ArrayList<>();
    List<String> regions = new ArrayList<>();

    @Scheduled(fixedDelay = 10000)
    public void leagueExpV4() throws InterruptedException {
        log.info("leagueExpV4:: BEGIN");
        List<ChallengerLeagues> response = null;
        try {
            for (ConfigUrl url2 : configUrlList) {
                for (int i = 1; ; i++) {
                    String url = url2.getUrl() + i;
                    log.info(url);
                    response = connectorClient.get(XRiotToken,
                            url,
                            new ParameterizedTypeReference<List<ChallengerLeagues>>() {
                            });
                    log.info(response.toString());
                    if (response != null && response.size() != 0) {
                        for (ChallengerLeagues challengerLeagues : response) {
                            challengerLeagues.setRegion(url2.getRegion());
                            challengerLeaguesRepository.save(challengerLeagues);
                        }
                        log.info("leagueExpV4:: END");
                    } else {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            log.info(e.getMessage());
        }
    }

    @PostMapping("/config/setting-jod")
    public ConfigSettingJodResponse configSettingJod(@RequestBody ConfigSettingJodRequest request) {
        log.info("configSettingJod :BEGIN");
        ConfigSettingJodResponse response = new ConfigSettingJodResponse();
        try {
            List<String> listReg = new ArrayList<>();
            List<ConfigUrl> x = new ArrayList<>();
            for (Config config : request.getList()) {
                ConfigUrl configUrl = new ConfigUrl();
                if (config.getRoles().equals(ConfigUtils.JOD) && config.getKey().equals(ConfigUtils.URLLEAGUEEXPV4)) {
                    configUrl.setUrl(config.getUrl());
                    configUrl.setRegion(config.getRegion());
                    x.add(configUrl);
                }
                if (config.getRoles().equals(ConfigUtils.XRIOTTOKEN)) {
                    XRiotToken = config.getValue();
                }
                if (config.getRoles().equals(ConfigUtils.REGION)) {
                    listReg.add(config.getValue());
                }
            }
            regions.clear();
            regions = listReg;
            configUrlList.clear();
            configUrlList = x;

            response.getStatus().setCode(Status.SUCCESS);
        } catch (Exception e) {
            log.info(e.getMessage());
            response.getStatus().setCode(Status.FAIL);
            return response;
        }
        log.info("configSettingJod:END");
        return response;
    }
}
