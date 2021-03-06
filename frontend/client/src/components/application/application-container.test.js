import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import { mount, shallow } from 'enzyme';
import Application from './application-container';
import { ACCEPTED_STATUS } from '../../constants/application/application-constants';
import configureStore from 'redux-mock-store';
import thunk from 'redux-thunk';
import { Provider } from 'react-redux';

jest.mock('react-i18next', () => ({
  useTranslation: () => {
    const useMock = [k => k, {}];
    useMock.t = k => k;
    useMock.i18n = {};
    return useMock;
  }
}));

const person = {
  firstName: 'firstName',
  lastName: 'lastName',
  email: 'email',
  ssn: '1231231231'
};

const application = {
  id: 1,
  status: {
    name: ACCEPTED_STATUS
  },
  person,
  competenceProfile: [],
  availability: []
};

const match = {
  params: {
    id: 1
  }
};

const defaultStore = {
  application: {
    application
  },
  error: {
    msg: ''
  },
  success: {
    msg: ''
  }
};

const mockStore = configureStore([thunk])(defaultStore);

describe('running application-container test', () => {
  it('unit renders correctly', () => {
    const wrapper = shallow(
      <Provider store={mockStore}>
        <Application match={match} />
      </Provider>
    );
    expect(wrapper.exists()).toBe(true);
    expect(wrapper).toMatchSnapshot();
  });

  it('integration render without crashing', () => {
    const wrapper = mount(
      <Provider store={mockStore}>
        <Router>
          <Application match={match} />
        </Router>
      </Provider>
    );
    expect(wrapper.exists()).toBe(true);
  });
});
