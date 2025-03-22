/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { BaseResponseBoolean } from '../models/BaseResponseBoolean';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class CopyLogControllerService {
    /**
     * @param codeId
     * @returns BaseResponseBoolean OK
     * @throws ApiError
     */
    public static add(
        codeId: string,
    ): CancelablePromise<BaseResponseBoolean> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/log/copy/add/{codeId}',
            path: {
                'codeId': codeId,
            },
        });
    }
}
