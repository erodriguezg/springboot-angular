import * as _ from 'lodash';
import {environmentCommons} from './environment.commons';
import {EnvironmentType} from './environment-type';

const environmentType: EnvironmentType = {
    production: true,
    ENV_NAME: 'qa',
    API_URL: 'http://10.5.1.47:8080'
};

export const ENVIRONMENT = _.merge(environmentCommons, environmentType);
