import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import { mount, shallow } from 'enzyme';
import Applications from './applications-container';

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

const defaultStore = {
  application: {
    applications: []
  }
};

const mockStore = configureStore([thunk])(defaultStore);

describe('running applications-container test', () => {
  it('unit renders correctly', () => {
    const wrapper = shallow(
      <Provider store={mockStore}>
        <Applications />
      </Provider>
    );
    expect(wrapper.exists()).toBe(true);
    expect(wrapper).toMatchSnapshot();
  });

  it('integration render without crashing', () => {
    const wrapper = mount(
      <Provider store={mockStore}>
        <Router>
          <Applications />
        </Router>
      </Provider>
    );
    expect(wrapper.exists()).toBe(true);
  });
});
