package com.example.microriot.model.request;

import com.example.microriot.model.Config;
import com.example.microriot.model.base.BaseRequest;
import lombok.Data;

import java.util.List;
@Data
public class ConfigSettingJodRequest extends BaseRequest {
    List<Config> list;
}
