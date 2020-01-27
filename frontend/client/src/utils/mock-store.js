import React from 'react';
import { Provider } from 'react-redux';
import { mount } from 'enzyme';
import configureMockStore from 'redux-mock-store';

const defaultStore = { auth: { isAuthenticated: true } };
const mockedStore = configureMockStore()(defaultStore);

export const mountWithProvider = children => (store = mockedStore) =>
  mount(<Provider store={store}>{children}</Provider>);
