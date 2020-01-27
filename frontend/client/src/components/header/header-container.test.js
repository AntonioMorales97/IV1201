import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import { shallow, mount } from 'enzyme';
import HeaderContainer from './header-container';
import configureStore from 'redux-mock-store';
import thunk from 'redux-thunk';
import { Provider } from 'react-redux';
/*
const instance = {
  language: 'en',
  languages: ['en', 'fr'],
  services: {
    resourceStore: {
      data: {}
    },
    backendConnector: {
      backend: {},
      state: { 'en|notLoadedNS': 1, 'fr|notLoadedNS': 1 }
    }
  },
  isInitialized: true,
  changeLanguage: () => {},
  getFixedT: () => message => message,
  hasResourceBundle: (lng, ns) => ns === 'alreadyLoadedNS',
  loadNamespaces: () => {},
  on: () => {},
  options: { fallbackLng: 'en' }
};
*/

const defaultStore = {
  auth: {
    isAuthenticated: false
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
  withTranslation: () => Component => {
    Component.defaultProps = {
      ...Component.defaultProps,
      t: () => '',
      i18n: { options: { fallbackLng: 'en' } }
    };
    return Component;
  }
}));

describe('running header-container test', () => {
  it('unit renders correctly', () => {
    const wrapper = shallow(
      <Provider store={mockStore}>
        <HeaderContainer></HeaderContainer>
      </Provider>
    );
    expect(wrapper.exists()).toBe(true);
    expect(wrapper).toMatchSnapshot();
  });

  it('integration renders without crashing', () => {
    const wrapper = mount(
      <Provider store={mockStore}>
        <Router>
          <HeaderContainer></HeaderContainer>
        </Router>
      </Provider>
    );
    expect(wrapper.exists()).toBe(true);
  });
});
