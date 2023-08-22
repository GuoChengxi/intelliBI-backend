package com.proj.intelliBI.manager;

import com.proj.intelliBI.common.ErrorCode;
import com.proj.intelliBI.constant.CommonConstant;
import com.proj.intelliBI.exception.BusinessException;
import com.yupi.yucongming.dev.client.YuCongMingClient;
import com.yupi.yucongming.dev.common.BaseResponse;
import com.yupi.yucongming.dev.model.DevChatRequest;
import com.yupi.yucongming.dev.model.DevChatResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

/**
 * Handle Request to AI api
 */
@Service
public class AIManager {

    @Resource
    private YuCongMingClient yuCongMingClient;

    /**
     * Chat with AI
     */
    @Async
    public String chatWithAI(String message) {
        if (message == null || message.length() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "Input message is blank");
        }
        DevChatRequest devChatRequest = new DevChatRequest();
        devChatRequest.setModelId(CommonConstant.BI_MODEL_ID);
        devChatRequest.setMessage(message);
        BaseResponse<DevChatResponse> response = yuCongMingClient.doChat(devChatRequest);
        if (response == null || response.getData() == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "AI Response Error");
        }
        return response.getData().getContent();
    }

    /**
     * Chat with AI (With Time Control)
     */
    @Async
    public CompletableFuture<String> chatWithAIAsync(String message) {
        if (message == null || message.length() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "Input message is blank");
        }
        DevChatRequest devChatRequest = new DevChatRequest();
        devChatRequest.setModelId(CommonConstant.BI_MODEL_ID);
        devChatRequest.setMessage(message);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            BaseResponse<DevChatResponse> response = yuCongMingClient.doChat(devChatRequest);
            if (response == null || response.getData() == null) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "AI Response Error");
            }
            return response.getData().getContent();
        });
        return future;
    }
}