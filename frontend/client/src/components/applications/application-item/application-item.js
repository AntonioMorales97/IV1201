import React from 'react';
import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';
import { useTranslation } from 'react-i18next';
import { ListGroupItem } from 'reactstrap';

/**
 * An ApplicationItem (ListGroupItem) to display an application's metadata.
 */
const ApplicationItem = props => {
  const { id, firstName, lastName, ssn, status } = props.application;
  const { t } = useTranslation();

  const badgeStatus = () => {
    if (status.name === 'accepted') {
      return 'badge-success';
    } else if (status.name === 'rejected') {
      return 'badge-danger';
    } else {
      return 'badge-secondary';
    }
  };
  return (
    <ListGroupItem className='row d-flex'>
      <div className='col-12 col-md-3'>{firstName + ' ' + lastName}</div>
      <div className='col-12 col-md-3'>{ssn}</div>
      <div className='col-12 col-md-3'>
        <span className={'badge ' + badgeStatus()}>
          {t(`application.${status.name}`)}
        </span>
      </div>
      <div className='col-12 col-md-3'>
        <Link to={`/applications/${id}`}>
          {t('application.view_application')}
        </Link>
      </div>
    </ListGroupItem>
  );
};
ApplicationItem.propTypes = {
  application: PropTypes.object.isRequired
};
export default ApplicationItem;
