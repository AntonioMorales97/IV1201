import React from 'react';
import { shallow } from 'enzyme';
import RegisterView from './register-view';

jest.mock('react-i18next', () => ({
  useTranslation: () => {
    const useMock = [k => k, {}];
    useMock.t = k => k;
    return useMock;
  }
}));

describe('running register-view test', () => {
  it('renders stateless component without crashing and correctly', () => {
    const wrapper = shallow(
      <RegisterView
        renderRedirect={jest.fn()}
        onSubmit={jest.fn()}
        onChange={jest.fn()}
      />
    );
    expect(wrapper.exists()).toBe(true);
    expect(wrapper.instance()).toEqual(null);
    expect(
      wrapper.containsMatchingElement(
        <div className='h3 text-center'>register</div>
      )
    ).toEqual(true);
    expect(wrapper).toMatchSnapshot();
  });
});
