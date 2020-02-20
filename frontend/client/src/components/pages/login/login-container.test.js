import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import { mount, shallow } from 'enzyme';
import Login from './login-container';

import configureStore from 'redux-mock-store';
import thunk from 'redux-thunk';
import { Provider } from 'react-redux';

const defaultStore = {
  auth: {
    isAuthenticated: false,
    user: {
      name: 'test'
    }
  },
  error: {
    msg: ''
  },
  success: {
    msg: ''
  }
};

const mockStore = configureStore([thunk])(defaultStore);

jest.mock('react-i18next', () => ({
  useTranslation: () => {
    const useMock = [k => k, {}];
    useMock.t = k => k;
    useMock.i18n = {};
    return useMock;
  }
}));

describe('running login-container test', () => {
  it('unit renders correctly', () => {
    const wrapper = shallow(
      <Provider store={mockStore}>
        <Login />
      </Provider>
    );
    expect(wrapper.exists()).toBe(true);
    expect(wrapper).toMatchSnapshot();
  });

  it('integration render without crashing', () => {
    const wrapper = mount(
      <Provider store={mockStore}>
        <Router>
          <Login />
        </Router>
      </Provider>
    );
    expect(wrapper.exists()).toBe(true);
  });
});
