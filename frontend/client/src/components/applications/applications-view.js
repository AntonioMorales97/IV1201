import React from 'react';
import PropTypes from 'prop-types';
import { useTranslation } from 'react-i18next';
import { Container, ListGroup } from 'reactstrap';
import ApplicationItem from './application-item';
import './applications.css';

/**
 * Renders a list of ApplicationItem:s
 */
const ApplicationsView = props => {
  const { t } = useTranslation();
  const { applications } = props;
  return (
    <Container className='applications'>
      <h1>{t('application.applications')}</h1>
      <ListGroup>
        {applications.length > 0 ? (
          applications.map(application => (
            <ApplicationItem key={application.id} application={application} />
          ))
        ) : (
          <h4>{t('application.no_applications')}</h4>
        )}
      </ListGroup>
    </Container>
  );
};

ApplicationsView.propTypes = {
  applications: PropTypes.array.isRequired
};

export default ApplicationsView;
