import React, { Component } from 'react';
import PropTypes from 'prop-types';
import HeaderView from './header-view';
import _ from 'lodash';
import { withTranslation } from 'react-i18next';
import { DropdownItem } from 'reactstrap';
import { logout } from '../../actions/auth/auth-actions';
import { connect } from 'react-redux';

const languageMappings = {
  en: 'English',
  se: 'Svenska'
};

/**
 * Holds the logic and state of the header. Renders the header.
 */
class HeaderContainer extends Component {
  state = {
    isOpen: false
  };

  static propTypes = {
    logout: PropTypes.func.isRequired,
    auth: PropTypes.object.isRequired
  };

  toggle = () => {
    this.setState({
      isOpen: !this.state.isOpen
    });
  };

  logout = () => {
    this.toggle();
    this.props.logout();
  };

  languageCodeToName = languageCode => {
    return _.get(languageMappings, languageCode, null);
  };

  render() {
    const { t, i18n } = this.props;
    const fallBackLanguage = i18n.options.fallbackLng;
    const currentLanguage = this.languageCodeToName(i18n.language)
      ? i18n.language
      : fallBackLanguage;
    const dropDownItems = _.keys(languageMappings).map(languageCode => {
      return (
        <DropdownItem
          onClick={() => i18n.changeLanguage(languageCode)}
          key={`${languageCode} DropDownItem`}
        >
          {this.languageCodeToName(languageCode)}
        </DropdownItem>
      );
    });
    const auth = this.props.auth;
    return (
      <HeaderView
        isOpen={this.state.isOpen}
        toggle={this.toggle}
        t={t}
        dropDownItems={dropDownItems}
        currentLanguage={currentLanguage}
        languageCodeToName={this.languageCodeToName}
        auth={auth}
        logout={this.logout}
      />
    );
  }
}

const mapStateToProps = state => ({
  auth: state.auth
});

export { HeaderContainer };
export default connect(mapStateToProps, { logout })(
  withTranslation()(HeaderContainer)
);
