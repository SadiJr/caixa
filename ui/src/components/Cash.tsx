import {
    Avatar,
    Box,
    Button,
    Container,
    CssBaseline,
    Grid,
    TextField,
    Typography,
    Menu,
    MenuItem
  } from "@mui/material";
import { LockOutlined } from "@mui/icons-material";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import { API_BASE_URL, ACCESS_TOKEN_NAME } from "../constants/apiConstants";
import * as React from 'react';

const Cash = () => {
    interface QueryMovimentation {
      year: number;
      month: number;
      deskId: number;
    }

    const navigate = useNavigate()
    const [year, setYear] = useState(2024);
    const [month, setMonth] = useState(11);
    const [cashDesiId, setCashDeskId] = useState(1);

    const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
    const open = Boolean(anchorEl);

    const [anchorEl2, setAnchorEl2] = React.useState<null | HTMLElement>(null);
    const open2 = Boolean(anchorEl2);

    const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
      setAnchorEl(event.currentTarget);
    };

    const handleClick2 = (event: React.MouseEvent<HTMLButtonElement>) => {
      setAnchorEl2(event.currentTarget);
    };

    const handleClose = () => {
      setAnchorEl(null);
      setAnchorEl2(null);
    };

    const handleYear = (event: React.MouseEvent<HTMLButtonElement>) => {
      // setAnchorEl(event.currentTarget);
      var token = localStorage.getItem(ACCESS_TOKEN_NAME);
      setYear(event.currentTarget.textContent);
    };

    const handleMonth = (event: React.MouseEvent<HTMLButtonElement>) => {
      var token = localStorage.getItem(ACCESS_TOKEN_NAME);
      setMonth(event.currentTarget.tabIndex + 1);
    };
  
    return (
      <>
        <Container maxWidth="xs">
          <CssBaseline />
          <Box
            sx={{
              mt: 20,
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: "primary.light" }}>
              <LockOutlined />
            </Avatar>
            <Typography variant="h5">Consultar caixa</Typography>
            <Button
        id="basic-button"
        aria-controls={open ? 'basic-menu' : undefined}
        aria-haspopup="true"
        aria-expanded={open ? 'true' : undefined}
        onClick={handleClick}
      >
        Ano
      </Button>
      <Menu
        id="basic-menu"
        anchorEl={anchorEl}
        open={open}
        onClose={handleClose}
        MenuListProps={{
          'aria-labelledby': 'basic-button',
        }}
      >
        <MenuItem onClick={handleYear}>2022</MenuItem>
        <MenuItem onClick={handleYear}>2023</MenuItem>
        <MenuItem onClick={handleYear}>2024</MenuItem>
      </Menu>
      </Box>

      <Box>
      <Button
        id="month-button"
        aria-controls={open ? 'month-menu' : undefined}
        aria-haspopup="true"
        aria-expanded={open2 ? 'true' : undefined}
        onClick={handleClick2}
      >
        Mês
      </Button>
      <Menu
        id="month-menu"
        anchorEl2={anchorEl2}
        open={open2}
        onClose={handleClose}
        MenuListProps={{
          'aria-labelledby': 'month-button',
        }}
      >
        <MenuItem onClick={handleMonth}>Janeiro</MenuItem>
        <MenuItem onClick={handleMonth}>Fevereiro</MenuItem>
        <MenuItem onClick={handleMonth}>Março</MenuItem>
        <MenuItem onClick={handleMonth}>Abril</MenuItem>
        <MenuItem onClick={handleMonth}>Maio</MenuItem>
        <MenuItem onClick={handleMonth}>Junho</MenuItem>
        <MenuItem onClick={handleMonth}>Julho</MenuItem>
        <MenuItem onClick={handleMonth}>Agosto</MenuItem>
        <MenuItem onClick={handleMonth}>Setembro</MenuItem>
        <MenuItem onClick={handleMonth}>Outubro</MenuItem>
        <MenuItem onClick={handleMonth}>Novembro</MenuItem>
        <MenuItem onClick={handleMonth}>Dezembro</MenuItem>
      </Menu>
          </Box>
        </Container>
      </>
    );
  };
  
  export default Cash;