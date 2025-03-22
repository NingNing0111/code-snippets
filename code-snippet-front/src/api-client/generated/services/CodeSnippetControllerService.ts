/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { BaseResponseBoolean } from "../models/BaseResponseBoolean";
import type { BaseResponseListCodeSnippetVO } from "../models/BaseResponseListCodeSnippetVO";
import type { BaseResponseString } from "../models/BaseResponseString";
import type { CodeSnippetVO } from "../models/CodeSnippetVO";
import type { CancelablePromise } from "../core/CancelablePromise";
import { OpenAPI } from "../core/OpenAPI";
import { request as __request } from "../core/request";
export class CodeSnippetControllerService {
  /**
   * @param authorization
   * @param requestBody
   * @returns BaseResponseBoolean OK
   * @throws ApiError
   */
  public static delete(
    authorization: string,
    requestBody: CodeSnippetVO
  ): CancelablePromise<BaseResponseBoolean> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/codeSnippet/deleteCodeSnippet",
      headers: {
        Authorization: authorization,
      },
      body: requestBody,
      mediaType: "application/json",
    });
  }
  /**
   * @param authorization
   * @param requestBody
   * @returns BaseResponseString OK
   * @throws ApiError
   */
  public static add1(
    authorization: string,
    requestBody: CodeSnippetVO
  ): CancelablePromise<BaseResponseString> {
    return __request(OpenAPI, {
      method: "POST",
      url: "/codeSnippet/addCodeSnippet",
      headers: {
        Authorization: authorization,
      },
      body: requestBody,
      mediaType: "application/json",
    });
  }
  /**
   * @param vo
   * @returns BaseResponseListCodeSnippetVO OK
   * @throws ApiError
   */
  public static list(
    vo: CodeSnippetVO
  ): CancelablePromise<BaseResponseListCodeSnippetVO> {
    return __request(OpenAPI, {
      method: "GET",
      url: "/codeSnippet/list",
      query: {
        ...vo,
      },
    });
  }
}
