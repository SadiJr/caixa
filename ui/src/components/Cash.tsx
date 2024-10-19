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
    const navigate = useNavigate()
    const [year, setYear] = useState(null);
    const [month, setMonth] = useState(null);
    const [cashDesiId, setCashDeskId] = useState(null);

    const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
    const open = Boolean(anchorEl);

    const [anchorEf, setAnchorEf] = React.useState<null | HTMLElement>(null);
    const openMonth = Boolean(anchorEf);

    const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
      setAnchorEl(event.currentTarget);
    };

    const handleClickMonth = (event: React.MouseEvent<HTMLButtonElement>) => {
      setAnchorEf(event.currentTarget);
    };

    const handleClose = () => {
      setAnchorEl(null);
    };

    const handleCloseMonth = () => {
      setAnchorEf(null);
    };

    const handleYear = (event: React.MouseEvent<HTMLButtonElement>) => {
      // setAnchorEl(event.currentTarget);
      var token = localStorage.getItem(ACCESS_TOKEN_NAME);
      setYear(event.currentTarget.textContent);
      console.log(year);
    };

    const handleMonth = (event: React.MouseEvent<HTMLButtonElement>) => {
      var token = localStorage.getItem(ACCESS_TOKEN_NAME);
      console.log(event.currentTarget.tag);
      setMonth(event.currentTarget.tabIndex + 1);
      console.log(month);
    };
  
    return (
      <>
        <Container maxWidth="xs">
          <CssBaseline />
          <Box
            sx={{
              mt: 100,
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
            <Button
              id="month-button"
              aria-controls={openMonth ? 'basic-menu' : undefined}
              aria-haspopup="true"
              aria-expanded={openMonth ? 'true' : undefined}
              onClick={handleClickMonth}
            >
              Mês
            </Button>
            
            <Menu
              id="month-menu"
              anchorEl={anchorEf}
              open={openMonth}
              onClose={handleCloseMonth}
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